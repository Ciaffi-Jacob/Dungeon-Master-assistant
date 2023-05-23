import { IGame } from 'app/shared/model/game.model';
import { IProfile } from 'app/shared/model/profile.model';

export interface ICharacter {
  id?: number;
  uid?: number | null;
  name?: string | null;
  level?: number | null;
  strength?: number | null;
  strengthProficiency?: boolean | null;
  dexterity?: number | null;
  dexterityProficiency?: boolean | null;
  constitution?: number | null;
  constitutionProficiency?: boolean | null;
  intelligence?: number | null;
  intelligenceProficiency?: boolean | null;
  charisma?: number | null;
  charismaProficiency?: boolean | null;
  wisdom?: number | null;
  wisdomProficiency?: boolean | null;
  passiveInsight?: number | null;
  passivePerception?: number | null;
  acrobatics?: boolean | null;
  animalHandling?: boolean | null;
  arcana?: boolean | null;
  athletics?: boolean | null;
  deception?: boolean | null;
  history?: boolean | null;
  insight?: boolean | null;
  intimidation?: boolean | null;
  investigation?: boolean | null;
  medicine?: boolean | null;
  nature?: boolean | null;
  perception?: boolean | null;
  performance?: boolean | null;
  persuasion?: boolean | null;
  religion?: boolean | null;
  sleightOfHand?: boolean | null;
  stealth?: boolean | null;
  survival?: boolean | null;
  game?: IGame | null;
  profile?: IProfile | null;
}

export const defaultValue: Readonly<ICharacter> = {
  strengthProficiency: false,
  dexterityProficiency: false,
  constitutionProficiency: false,
  intelligenceProficiency: false,
  charismaProficiency: false,
  wisdomProficiency: false,
  acrobatics: false,
  animalHandling: false,
  arcana: false,
  athletics: false,
  deception: false,
  history: false,
  insight: false,
  intimidation: false,
  investigation: false,
  medicine: false,
  nature: false,
  perception: false,
  performance: false,
  persuasion: false,
  religion: false,
  sleightOfHand: false,
  stealth: false,
  survival: false,
};
