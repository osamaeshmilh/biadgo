import {TestBed} from '@angular/core/testing';
import {provideRouter, withComponentInputBinding} from '@angular/router';
import {RouterTestingHarness, RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {DriverWalletDetailComponent} from './driver-wallet-detail.component';

describe('DriverWallet Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DriverWalletDetailComponent, RouterTestingModule.withRoutes([], {bindToComponentInputs: true})],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: DriverWalletDetailComponent,
              resolve: {driverWallet: () => of({id: 123})},
            },
          ],
          withComponentInputBinding()
        ),
      ],
    })
      .overrideTemplate(DriverWalletDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load driverWallet on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', DriverWalletDetailComponent);

      // THEN
      expect(instance.driverWallet).toEqual(expect.objectContaining({id: 123}));
    });
  });
});
