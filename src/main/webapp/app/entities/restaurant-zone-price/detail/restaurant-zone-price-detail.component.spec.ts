import {TestBed} from '@angular/core/testing';
import {provideRouter, withComponentInputBinding} from '@angular/router';
import {RouterTestingHarness, RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {RestaurantZonePriceDetailComponent} from './restaurant-zone-price-detail.component';

describe('RestaurantZonePrice Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RestaurantZonePriceDetailComponent, RouterTestingModule.withRoutes([], {bindToComponentInputs: true})],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: RestaurantZonePriceDetailComponent,
              resolve: {restaurantZonePrice: () => of({id: 123})},
            },
          ],
          withComponentInputBinding()
        ),
      ],
    })
      .overrideTemplate(RestaurantZonePriceDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load restaurantZonePrice on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', RestaurantZonePriceDetailComponent);

      // THEN
      expect(instance.restaurantZonePrice).toEqual(expect.objectContaining({id: 123}));
    });
  });
});
