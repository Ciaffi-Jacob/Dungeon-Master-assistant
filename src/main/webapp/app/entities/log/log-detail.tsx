import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './log.reducer';

export const LogDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const logEntity = useAppSelector(state => state.log.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="logDetailsHeading">Log</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{logEntity.id}</dd>
          <dt>
            <span id="entry">Entry</span>
          </dt>
          <dd>{logEntity.entry}</dd>
          <dt>Game</dt>
          <dd>{logEntity.game ? logEntity.game.id : ''}</dd>
          <dt>Profile</dt>
          <dd>{logEntity.profile ? logEntity.profile.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/log" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/log/${logEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default LogDetail;
