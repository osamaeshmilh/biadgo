import {TestBed} from '@angular/core/testing';
import {provideRouter, withComponentInputBinding} from '@angular/router';
import {RouterTestingHarness, RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {CustomerWalletDetailComponent} from './customer-wallet-detail.component';

describe('CustomerWallet Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CustomerWalletDetailComponent, RouterTestingModule.withRoutes([], {bindToComponentInputs: true})],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: CustomerWalletDetailComponent,
              resolve: {customerWallet: () => of({id: 123})},
            },
          ],
          withComponentInputBinding()
        ),
      ],
    })
      .overrideTemplate(CustomerWalletDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load customerWallet on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', CustomerWalletDetailComponent);

      // THEN
      expect(instance.customerWallet).toEqual(expect.objectContaining({id: 123}));
    });
  });
});
