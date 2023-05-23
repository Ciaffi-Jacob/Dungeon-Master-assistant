import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Log from './log';
import Profile from './profile';
import Game from './game';
import Character from './character';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="log/*" element={<Log />} />
        <Route path="profile/*" element={<Profile />} />
        <Route path="game/*" element={<Game />} />
        <Route path="character/*" element={<Character />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
