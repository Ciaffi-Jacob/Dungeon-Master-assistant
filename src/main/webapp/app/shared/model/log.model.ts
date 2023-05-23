import { IGame } from 'app/shared/model/game.model';
import { IProfile } from 'app/shared/model/profile.model';

export interface ILog {
  id?: number;
  entry?: string | null;
  game?: IGame | null;
  profile?: IProfile | null;
}

export const defaultValue: Readonly<ILog> = {};
