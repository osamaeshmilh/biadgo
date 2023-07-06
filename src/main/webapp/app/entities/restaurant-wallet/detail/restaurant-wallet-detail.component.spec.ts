import {TestBed} from '@angular/core/testing';
import {provideRouter, withComponentInputBinding} from '@angular/router';
import {RouterTestingHarness, RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {RestaurantWalletDetailComponent} from './restaurant-wallet-detail.component';

describe('RestaurantWallet Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RestaurantWalletDetailComponent, RouterTestingModule.withRoutes([], {bindToComponentInputs: true})],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: RestaurantWalletDetailComponent,
              resolve: {restaurantWallet: () => of({id: 123})},
            },
          ],
          withComponentInputBinding()
        ),
      ],
    })
      .overrideTemplate(RestaurantWalletDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load restaurantWallet on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', RestaurantWalletDetailComponent);

      // THEN
      expect(instance.restaurantWallet).toEqual(expect.objectContaining({id: 123}));
    });
  });
});
