import {TestBed} from '@angular/core/testing';
import {provideRouter, withComponentInputBinding} from '@angular/router';
import {RouterTestingHarness, RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {RestaurantScheduleDetailComponent} from './restaurant-schedule-detail.component';

describe('RestaurantSchedule Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RestaurantScheduleDetailComponent, RouterTestingModule.withRoutes([], {bindToComponentInputs: true})],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: RestaurantScheduleDetailComponent,
              resolve: {restaurantSchedule: () => of({id: 123})},
            },
          ],
          withComponentInputBinding()
        ),
      ],
    })
      .overrideTemplate(RestaurantScheduleDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load restaurantSchedule on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', RestaurantScheduleDetailComponent);

      // THEN
      expect(instance.restaurantSchedule).toEqual(expect.objectContaining({id: 123}));
    });
  });
});
