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
import { ICharacter } from 'app/shared/model/character.model';
import { getEntity, updateEntity, createEntity, reset } from './character.reducer';

export const CharacterUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const games = useAppSelector(state => state.game.entities);
  const profiles = useAppSelector(state => state.profile.entities);
  const characterEntity = useAppSelector(state => state.character.entity);
  const loading = useAppSelector(state => state.character.loading);
  const updating = useAppSelector(state => state.character.updating);
  const updateSuccess = useAppSelector(state => state.character.updateSuccess);

  const handleClose = () => {
    navigate('/character');
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
      ...characterEntity,
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
          ...characterEntity,
          game: characterEntity?.game?.id,
          profile: characterEntity?.profile?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="dungeonMasterAssistantApp.character.home.createOrEditLabel" data-cy="CharacterCreateUpdateHeading">
            Create or edit a Character
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="character-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Uid" id="character-uid" name="uid" data-cy="uid" type="text" />
              <ValidatedField label="Name" id="character-name" name="name" data-cy="name" type="text" />
              <ValidatedField label="Level" id="character-level" name="level" data-cy="level" type="text" />
              <ValidatedField label="Strength" id="character-strength" name="strength" data-cy="strength" type="text" />
              <ValidatedField
                label="Strength Proficiency"
                id="character-strengthProficiency"
                name="strengthProficiency"
                data-cy="strengthProficiency"
                check
                type="checkbox"
              />
              <ValidatedField label="Dexterity" id="character-dexterity" name="dexterity" data-cy="dexterity" type="text" />
              <ValidatedField
                label="Dexterity Proficiency"
                id="character-dexterityProficiency"
                name="dexterityProficiency"
                data-cy="dexterityProficiency"
                check
                type="checkbox"
              />
              <ValidatedField label="Constitution" id="character-constitution" name="constitution" data-cy="constitution" type="text" />
              <ValidatedField
                label="Constitution Proficiency"
                id="character-constitutionProficiency"
                name="constitutionProficiency"
                data-cy="constitutionProficiency"
                check
                type="checkbox"
              />
              <ValidatedField label="Intelligence" id="character-intelligence" name="intelligence" data-cy="intelligence" type="text" />
              <ValidatedField
                label="Intelligence Proficiency"
                id="character-intelligenceProficiency"
                name="intelligenceProficiency"
                data-cy="intelligenceProficiency"
                check
                type="checkbox"
              />
              <ValidatedField label="Charisma" id="character-charisma" name="charisma" data-cy="charisma" type="text" />
              <ValidatedField
                label="Charisma Proficiency"
                id="character-charismaProficiency"
                name="charismaProficiency"
                data-cy="charismaProficiency"
                check
                type="checkbox"
              />
              <ValidatedField label="Wisdom" id="character-wisdom" name="wisdom" data-cy="wisdom" type="text" />
              <ValidatedField
                label="Wisdom Proficiency"
                id="character-wisdomProficiency"
                name="wisdomProficiency"
                data-cy="wisdomProficiency"
                check
                type="checkbox"
              />
              <ValidatedField
                label="Passive Insight"
                id="character-passiveInsight"
                name="passiveInsight"
                data-cy="passiveInsight"
                type="text"
              />
              <ValidatedField
                label="Passive Perception"
                id="character-passivePerception"
                name="passivePerception"
                data-cy="passivePerception"
                type="text"
              />
              <ValidatedField label="Acrobatics" id="character-acrobatics" name="acrobatics" data-cy="acrobatics" check type="checkbox" />
              <ValidatedField
                label="Animal Handling"
                id="character-animalHandling"
                name="animalHandling"
                data-cy="animalHandling"
                check
                type="checkbox"
              />
              <ValidatedField label="Arcana" id="character-arcana" name="arcana" data-cy="arcana" check type="checkbox" />
              <ValidatedField label="Athletics" id="character-athletics" name="athletics" data-cy="athletics" check type="checkbox" />
              <ValidatedField label="Deception" id="character-deception" name="deception" data-cy="deception" check type="checkbox" />
              <ValidatedField label="History" id="character-history" name="history" data-cy="history" check type="checkbox" />
              <ValidatedField label="Insight" id="character-insight" name="insight" data-cy="insight" check type="checkbox" />
              <ValidatedField
                label="Intimidation"
                id="character-intimidation"
                name="intimidation"
                data-cy="intimidation"
                check
                type="checkbox"
              />
              <ValidatedField
                label="Investigation"
                id="character-investigation"
                name="investigation"
                data-cy="investigation"
                check
                type="checkbox"
              />
              <ValidatedField label="Medicine" id="character-medicine" name="medicine" data-cy="medicine" check type="checkbox" />
              <ValidatedField label="Nature" id="character-nature" name="nature" data-cy="nature" check type="checkbox" />
              <ValidatedField label="Perception" id="character-perception" name="perception" data-cy="perception" check type="checkbox" />
              <ValidatedField
                label="Performance"
                id="character-performance"
                name="performance"
                data-cy="performance"
                check
                type="checkbox"
              />
              <ValidatedField label="Persuasion" id="character-persuasion" name="persuasion" data-cy="persuasion" check type="checkbox" />
              <ValidatedField label="Religion" id="character-religion" name="religion" data-cy="religion" check type="checkbox" />
              <ValidatedField
                label="Sleight Of Hand"
                id="character-sleightOfHand"
                name="sleightOfHand"
                data-cy="sleightOfHand"
                check
                type="checkbox"
              />
              <ValidatedField label="Stealth" id="character-stealth" name="stealth" data-cy="stealth" check type="checkbox" />
              <ValidatedField label="Survival" id="character-survival" name="survival" data-cy="survival" check type="checkbox" />
              <ValidatedField id="character-game" name="game" data-cy="game" label="Game" type="select">
                <option value="" key="0" />
                {games
                  ? games.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="character-profile" name="profile" data-cy="profile" label="Profile" type="select">
                <option value="" key="0" />
                {profiles
                  ? profiles.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/character" replace color="info">
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

export default CharacterUpdate;
