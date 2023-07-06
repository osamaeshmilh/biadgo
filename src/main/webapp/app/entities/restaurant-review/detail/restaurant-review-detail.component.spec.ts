import {TestBed} from '@angular/core/testing';
import {provideRouter, withComponentInputBinding} from '@angular/router';
import {RouterTestingHarness, RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {RestaurantReviewDetailComponent} from './restaurant-review-detail.component';

describe('RestaurantReview Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RestaurantReviewDetailComponent, RouterTestingModule.withRoutes([], {bindToComponentInputs: true})],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: RestaurantReviewDetailComponent,
              resolve: {restaurantReview: () => of({id: 123})},
            },
          ],
          withComponentInputBinding()
        ),
      ],
    })
      .overrideTemplate(RestaurantReviewDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load restaurantReview on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', RestaurantReviewDetailComponent);

      // THEN
      expect(instance.restaurantReview).toEqual(expect.objectContaining({id: 123}));
    });
  });
});
