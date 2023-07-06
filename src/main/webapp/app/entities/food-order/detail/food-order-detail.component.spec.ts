import {TestBed} from '@angular/core/testing';
import {provideRouter, withComponentInputBinding} from '@angular/router';
import {RouterTestingHarness, RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {FoodOrderDetailComponent} from './food-order-detail.component';

describe('FoodOrder Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FoodOrderDetailComponent, RouterTestingModule.withRoutes([], {bindToComponentInputs: true})],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: FoodOrderDetailComponent,
              resolve: {foodOrder: () => of({id: 123})},
            },
          ],
          withComponentInputBinding()
        ),
      ],
    })
      .overrideTemplate(FoodOrderDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load foodOrder on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', FoodOrderDetailComponent);

      // THEN
      expect(instance.foodOrder).toEqual(expect.objectContaining({id: 123}));
    });
  });
});
