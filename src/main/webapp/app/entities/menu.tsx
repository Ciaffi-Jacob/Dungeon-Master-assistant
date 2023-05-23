import React from 'react';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/log">
        Log
      </MenuItem>
      <MenuItem icon="asterisk" to="/profile">
        Profile
      </MenuItem>
      <MenuItem icon="asterisk" to="/game">
        Game
      </MenuItem>
      <MenuItem icon="asterisk" to="/character">
        Character
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
