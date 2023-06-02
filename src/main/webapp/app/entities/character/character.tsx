import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICharacter } from 'app/shared/model/character.model';
import { getEntities } from './character.reducer';

export const Character = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const characterList = useAppSelector(state => state.character.entities);
  const loading = useAppSelector(state => state.character.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="character-heading" data-cy="CharacterHeading">
        Characters
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh list
          </Button>
          <Link to="/character/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Character
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {characterList && characterList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>

                <th>Name</th>
                <th>Level</th>
                <th>Strength</th>
                <th>Strength Proficiency</th>
                <th>Dexterity</th>
                <th>Dexterity Proficiency</th>
                <th>Constitution</th>
                <th>Constitution Proficiency</th>
                <th>Intelligence</th>
                <th>Intelligence Proficiency</th>
                <th>Charisma</th>
                <th>Charisma Proficiency</th>
                <th>Wisdom</th>
                <th>Wisdom Proficiency</th>
                <th>Passive Insight</th>
                <th>Passive Perception</th>
                <th>Acrobatics</th>
                <th>Animal Handling</th>
                <th>Arcana</th>
                <th>Athletics</th>
                <th>Deception</th>
                <th>History</th>
                <th>Insight</th>
                <th>Intimidation</th>
                <th>Investigation</th>
                <th>Medicine</th>
                <th>Nature</th>
                <th>Perception</th>
                <th>Performance</th>
                <th>Persuasion</th>
                <th>Religion</th>
                <th>Sleight Of Hand</th>
                <th>Stealth</th>
                <th>Survival</th>
                <th>Game</th>
                <th>Profile</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {characterList.map((character, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/character/${character.id}`} color="link" size="sm">
                      {character.id}
                    </Button>
                  </td>

                  <td>{character.name}</td>
                  <td>{character.level}</td>
                  <td>{character.strength}</td>
                  <td>{character.strengthProficiency ? 'true' : 'false'}</td>
                  <td>{character.dexterity}</td>
                  <td>{character.dexterityProficiency ? 'true' : 'false'}</td>
                  <td>{character.constitution}</td>
                  <td>{character.constitutionProficiency ? 'true' : 'false'}</td>
                  <td>{character.intelligence}</td>
                  <td>{character.intelligenceProficiency ? 'true' : 'false'}</td>
                  <td>{character.charisma}</td>
                  <td>{character.charismaProficiency ? 'true' : 'false'}</td>
                  <td>{character.wisdom}</td>
                  <td>{character.wisdomProficiency ? 'true' : 'false'}</td>
                  <td>{character.passiveInsight}</td>
                  <td>{character.passivePerception}</td>
                  <td>{character.acrobatics ? 'true' : 'false'}</td>
                  <td>{character.animalHandling ? 'true' : 'false'}</td>
                  <td>{character.arcana ? 'true' : 'false'}</td>
                  <td>{character.athletics ? 'true' : 'false'}</td>
                  <td>{character.deception ? 'true' : 'false'}</td>
                  <td>{character.history ? 'true' : 'false'}</td>
                  <td>{character.insight ? 'true' : 'false'}</td>
                  <td>{character.intimidation ? 'true' : 'false'}</td>
                  <td>{character.investigation ? 'true' : 'false'}</td>
                  <td>{character.medicine ? 'true' : 'false'}</td>
                  <td>{character.nature ? 'true' : 'false'}</td>
                  <td>{character.perception ? 'true' : 'false'}</td>
                  <td>{character.performance ? 'true' : 'false'}</td>
                  <td>{character.persuasion ? 'true' : 'false'}</td>
                  <td>{character.religion ? 'true' : 'false'}</td>
                  <td>{character.sleightOfHand ? 'true' : 'false'}</td>
                  <td>{character.stealth ? 'true' : 'false'}</td>
                  <td>{character.survival ? 'true' : 'false'}</td>
                  <td>{character.game ? <Link to={`/game/${character.game.id}`}>{character.game.id}</Link> : ''}</td>
                  <td>{character.profile ? <Link to={`/profile/${character.profile.id}`}>{character.profile.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/character/${character.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`/character/${character.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`/character/${character.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Characters found</div>
        )}
      </div>
    </div>
  );
};

export default Character;
