import { ILog } from 'app/shared/model/log.model';
import { ICharacter } from 'app/shared/model/character.model';

export interface IProfile {
  id?: number;
  userName?: string | null;
  password?: string | null;
  uid?: number | null;
  logs?: ILog[] | null;
  characters?: ICharacter[] | null;
}

export const defaultValue: Readonly<IProfile> = {};
