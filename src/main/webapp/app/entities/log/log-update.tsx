import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IGame } from 'app/shared/model/game.model';
import { getEntities as getGames } from 'app/entities/game/game.reducer';
import { IProfile } from 'app/shared/model/profile.model';
import { getEntities as getProfiles } from 'app/entities/profile/profile.reducer';
import { ILog } from 'app/shared/model/log.model';
import { getEntity, updateEntity, createEntity, reset } from './log.reducer';

export const LogUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const games = useAppSelector(state => state.game.entities);
  const profiles = useAppSelector(state => state.profile.entities);
  const logEntity = useAppSelector(state => state.log.entity);
  const loading = useAppSelector(state => state.log.loading);
  const updating = useAppSelector(state => state.log.updating);
  const updateSuccess = useAppSelector(state => state.log.updateSuccess);

  const handleClose = () => {
    navigate('/log');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getGames({}));
    dispatch(getProfiles({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...logEntity,
      ...values,
      game: games.find(it => it.id.toString() === values.game.toString()),
      profile: profiles.find(it => it.id.toString() === values.profile.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...logEntity,
          game: logEntity?.game?.id,
          profile: logEntity?.profile?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="dungeonMasterAssistantApp.log.home.createOrEditLabel" data-cy="LogCreateUpdateHeading">
            Create or edit a Log
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="log-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Entry" id="log-entry" name="entry" data-cy="entry" type="text" />
              <ValidatedField id="log-game" name="game" data-cy="game" label="Game" type="select">
                <option value="" key="0" />
                {games
                  ? games.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="log-profile" name="profile" data-cy="profile" label="Profile" type="select">
                <option value="" key="0" />
                {profiles
                  ? profiles.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/log" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default LogUpdate;
