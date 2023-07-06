import {TestBed} from '@angular/core/testing';
import {provideRouter, withComponentInputBinding} from '@angular/router';
import {RouterTestingHarness, RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {RestaurantDetailComponent} from './restaurant-detail.component';

describe('Restaurant Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RestaurantDetailComponent, RouterTestingModule.withRoutes([], {bindToComponentInputs: true})],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: RestaurantDetailComponent,
              resolve: {restaurant: () => of({id: 123})},
            },
          ],
          withComponentInputBinding()
        ),
      ],
    })
      .overrideTemplate(RestaurantDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load restaurant on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', RestaurantDetailComponent);

      // THEN
      expect(instance.restaurant).toEqual(expect.objectContaining({id: 123}));
    });
  });
});
