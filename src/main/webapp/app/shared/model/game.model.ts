import { ICharacter } from 'app/shared/model/character.model';
import { ILog } from 'app/shared/model/log.model';

export interface IGame {
  id?: number;
  uid?: number | null;
  characters?: ICharacter[] | null;
  log?: ILog | null;
}

export const defaultValue: Readonly<IGame> = {};
