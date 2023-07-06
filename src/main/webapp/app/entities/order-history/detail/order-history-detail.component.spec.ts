import {TestBed} from '@angular/core/testing';
import {provideRouter, withComponentInputBinding} from '@angular/router';
import {RouterTestingHarness, RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {OrderHistoryDetailComponent} from './order-history-detail.component';

describe('OrderHistory Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OrderHistoryDetailComponent, RouterTestingModule.withRoutes([], {bindToComponentInputs: true})],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: OrderHistoryDetailComponent,
              resolve: {orderHistory: () => of({id: 123})},
            },
          ],
          withComponentInputBinding()
        ),
      ],
    })
      .overrideTemplate(OrderHistoryDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load orderHistory on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OrderHistoryDetailComponent);

      // THEN
      expect(instance.orderHistory).toEqual(expect.objectContaining({id: 123}));
    });
  });
});
