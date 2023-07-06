import {TestBed} from '@angular/core/testing';
import {provideRouter, withComponentInputBinding} from '@angular/router';
import {RouterTestingHarness, RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {ReferralDetailComponent} from './referral-detail.component';

describe('Referral Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReferralDetailComponent, RouterTestingModule.withRoutes([], {bindToComponentInputs: true})],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: ReferralDetailComponent,
              resolve: {referral: () => of({id: 123})},
            },
          ],
          withComponentInputBinding()
        ),
      ],
    })
      .overrideTemplate(ReferralDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load referral on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', ReferralDetailComponent);

      // THEN
      expect(instance.referral).toEqual(expect.objectContaining({id: 123}));
    });
  });
});
