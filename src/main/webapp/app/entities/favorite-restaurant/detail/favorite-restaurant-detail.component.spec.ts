import {TestBed} from '@angular/core/testing';
import {provideRouter, withComponentInputBinding} from '@angular/router';
import {RouterTestingHarness, RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {FavoriteRestaurantDetailComponent} from './favorite-restaurant-detail.component';

describe('FavoriteRestaurant Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FavoriteRestaurantDetailComponent, RouterTestingModule.withRoutes([], {bindToComponentInputs: true})],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: FavoriteRestaurantDetailComponent,
              resolve: {favoriteRestaurant: () => of({id: 123})},
            },
          ],
          withComponentInputBinding()
        ),
      ],
    })
      .overrideTemplate(FavoriteRestaurantDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load favoriteRestaurant on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', FavoriteRestaurantDetailComponent);

      // THEN
      expect(instance.favoriteRestaurant).toEqual(expect.objectContaining({id: 123}));
    });
  });
});
