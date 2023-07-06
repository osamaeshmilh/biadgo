import {TestBed} from '@angular/core/testing';
import {provideRouter, withComponentInputBinding} from '@angular/router';
import {RouterTestingHarness, RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {RestaurantDistancePriceDetailComponent} from './restaurant-distance-price-detail.component';

describe('RestaurantDistancePrice Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RestaurantDistancePriceDetailComponent, RouterTestingModule.withRoutes([], {bindToComponentInputs: true})],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: RestaurantDistancePriceDetailComponent,
              resolve: {restaurantDistancePrice: () => of({id: 123})},
            },
          ],
          withComponentInputBinding()
        ),
      ],
    })
      .overrideTemplate(RestaurantDistancePriceDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load restaurantDistancePrice on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', RestaurantDistancePriceDetailComponent);

      // THEN
      expect(instance.restaurantDistancePrice).toEqual(expect.objectContaining({id: 123}));
    });
  });
});
