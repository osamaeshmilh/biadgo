import {TestBed} from '@angular/core/testing';
import {provideRouter, withComponentInputBinding} from '@angular/router';
import {RouterTestingHarness, RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {CouponDetailComponent} from './coupon-detail.component';

describe('Coupon Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CouponDetailComponent, RouterTestingModule.withRoutes([], {bindToComponentInputs: true})],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: CouponDetailComponent,
              resolve: {coupon: () => of({id: 123})},
            },
          ],
          withComponentInputBinding()
        ),
      ],
    })
      .overrideTemplate(CouponDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load coupon on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', CouponDetailComponent);

      // THEN
      expect(instance.coupon).toEqual(expect.objectContaining({id: 123}));
    });
  });
});
