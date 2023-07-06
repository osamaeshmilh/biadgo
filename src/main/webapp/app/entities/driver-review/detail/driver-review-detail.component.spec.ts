import {TestBed} from '@angular/core/testing';
import {provideRouter, withComponentInputBinding} from '@angular/router';
import {RouterTestingHarness, RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {DriverReviewDetailComponent} from './driver-review-detail.component';

describe('DriverReview Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DriverReviewDetailComponent, RouterTestingModule.withRoutes([], {bindToComponentInputs: true})],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: DriverReviewDetailComponent,
              resolve: {driverReview: () => of({id: 123})},
            },
          ],
          withComponentInputBinding()
        ),
      ],
    })
      .overrideTemplate(DriverReviewDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load driverReview on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', DriverReviewDetailComponent);

      // THEN
      expect(instance.driverReview).toEqual(expect.objectContaining({id: 123}));
    });
  });
});
