import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './character.reducer';

export const CharacterDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const characterEntity = useAppSelector(state => state.character.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="characterDetailsHeading">Character</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{characterEntity.id}</dd>
          <dt>
            <span id="uid">Uid</span>
          </dt>
          <dd>{characterEntity.uid}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{characterEntity.name}</dd>
          <dt>
            <span id="level">Level</span>
          </dt>
          <dd>{characterEntity.level}</dd>
          <dt>
            <span id="strength">Strength</span>
          </dt>
          <dd>{characterEntity.strength}</dd>
          <dt>
            <span id="strengthProficiency">Strength Proficiency</span>
          </dt>
          <dd>{characterEntity.strengthProficiency ? 'true' : 'false'}</dd>
          <dt>
            <span id="dexterity">Dexterity</span>
          </dt>
          <dd>{characterEntity.dexterity}</dd>
          <dt>
            <span id="dexterityProficiency">Dexterity Proficiency</span>
          </dt>
          <dd>{characterEntity.dexterityProficiency ? 'true' : 'false'}</dd>
          <dt>
            <span id="constitution">Constitution</span>
          </dt>
          <dd>{characterEntity.constitution}</dd>
          <dt>
            <span id="constitutionProficiency">Constitution Proficiency</span>
          </dt>
          <dd>{characterEntity.constitutionProficiency ? 'true' : 'false'}</dd>
          <dt>
            <span id="intelligence">Intelligence</span>
          </dt>
          <dd>{characterEntity.intelligence}</dd>
          <dt>
            <span id="intelligenceProficiency">Intelligence Proficiency</span>
          </dt>
          <dd>{characterEntity.intelligenceProficiency ? 'true' : 'false'}</dd>
          <dt>
            <span id="charisma">Charisma</span>
          </dt>
          <dd>{characterEntity.charisma}</dd>
          <dt>
            <span id="charismaProficiency">Charisma Proficiency</span>
          </dt>
          <dd>{characterEntity.charismaProficiency ? 'true' : 'false'}</dd>
          <dt>
            <span id="wisdom">Wisdom</span>
          </dt>
          <dd>{characterEntity.wisdom}</dd>
          <dt>
            <span id="wisdomProficiency">Wisdom Proficiency</span>
          </dt>
          <dd>{characterEntity.wisdomProficiency ? 'true' : 'false'}</dd>
          <dt>
            <span id="passiveInsight">Passive Insight</span>
          </dt>
          <dd>{characterEntity.passiveInsight}</dd>
          <dt>
            <span id="passivePerception">Passive Perception</span>
          </dt>
          <dd>{characterEntity.passivePerception}</dd>
          <dt>
            <span id="acrobatics">Acrobatics</span>
          </dt>
          <dd>{characterEntity.acrobatics ? 'true' : 'false'}</dd>
          <dt>
            <span id="animalHandling">Animal Handling</span>
          </dt>
          <dd>{characterEntity.animalHandling ? 'true' : 'false'}</dd>
          <dt>
            <span id="arcana">Arcana</span>
          </dt>
          <dd>{characterEntity.arcana ? 'true' : 'false'}</dd>
          <dt>
            <span id="athletics">Athletics</span>
          </dt>
          <dd>{characterEntity.athletics ? 'true' : 'false'}</dd>
          <dt>
            <span id="deception">Deception</span>
          </dt>
          <dd>{characterEntity.deception ? 'true' : 'false'}</dd>
          <dt>
            <span id="history">History</span>
          </dt>
          <dd>{characterEntity.history ? 'true' : 'false'}</dd>
          <dt>
            <span id="insight">Insight</span>
          </dt>
          <dd>{characterEntity.insight ? 'true' : 'false'}</dd>
          <dt>
            <span id="intimidation">Intimidation</span>
          </dt>
          <dd>{characterEntity.intimidation ? 'true' : 'false'}</dd>
          <dt>
            <span id="investigation">Investigation</span>
          </dt>
          <dd>{characterEntity.investigation ? 'true' : 'false'}</dd>
          <dt>
            <span id="medicine">Medicine</span>
          </dt>
          <dd>{characterEntity.medicine ? 'true' : 'false'}</dd>
          <dt>
            <span id="nature">Nature</span>
          </dt>
          <dd>{characterEntity.nature ? 'true' : 'false'}</dd>
          <dt>
            <span id="perception">Perception</span>
          </dt>
          <dd>{characterEntity.perception ? 'true' : 'false'}</dd>
          <dt>
            <span id="performance">Performance</span>
          </dt>
          <dd>{characterEntity.performance ? 'true' : 'false'}</dd>
          <dt>
            <span id="persuasion">Persuasion</span>
          </dt>
          <dd>{characterEntity.persuasion ? 'true' : 'false'}</dd>
          <dt>
            <span id="religion">Religion</span>
          </dt>
          <dd>{characterEntity.religion ? 'true' : 'false'}</dd>
          <dt>
            <span id="sleightOfHand">Sleight Of Hand</span>
          </dt>
          <dd>{characterEntity.sleightOfHand ? 'true' : 'false'}</dd>
          <dt>
            <span id="stealth">Stealth</span>
          </dt>
          <dd>{characterEntity.stealth ? 'true' : 'false'}</dd>
          <dt>
            <span id="survival">Survival</span>
          </dt>
          <dd>{characterEntity.survival ? 'true' : 'false'}</dd>
          <dt>Game</dt>
          <dd>{characterEntity.game ? characterEntity.game.id : ''}</dd>
          <dt>Profile</dt>
          <dd>{characterEntity.profile ? characterEntity.profile.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/character" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/character/${characterEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default CharacterDetail;
