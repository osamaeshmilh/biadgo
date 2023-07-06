import {TestBed} from '@angular/core/testing';
import {provideRouter, withComponentInputBinding} from '@angular/router';
import {RouterTestingHarness, RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {DeliveryAddressDetailComponent} from './delivery-address-detail.component';

describe('DeliveryAddress Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeliveryAddressDetailComponent, RouterTestingModule.withRoutes([], {bindToComponentInputs: true})],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: DeliveryAddressDetailComponent,
              resolve: {deliveryAddress: () => of({id: 123})},
            },
          ],
          withComponentInputBinding()
        ),
      ],
    })
      .overrideTemplate(DeliveryAddressDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load deliveryAddress on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', DeliveryAddressDetailComponent);

      // THEN
      expect(instance.deliveryAddress).toEqual(expect.objectContaining({id: 123}));
    });
  });
});
