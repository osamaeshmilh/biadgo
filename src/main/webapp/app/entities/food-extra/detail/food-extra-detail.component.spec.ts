import {TestBed} from '@angular/core/testing';
import {provideRouter, withComponentInputBinding} from '@angular/router';
import {RouterTestingHarness, RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {FoodExtraDetailComponent} from './food-extra-detail.component';

describe('FoodExtra Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FoodExtraDetailComponent, RouterTestingModule.withRoutes([], {bindToComponentInputs: true})],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: FoodExtraDetailComponent,
              resolve: {foodExtra: () => of({id: 123})},
            },
          ],
          withComponentInputBinding()
        ),
      ],
    })
      .overrideTemplate(FoodExtraDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load foodExtra on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', FoodExtraDetailComponent);

      // THEN
      expect(instance.foodExtra).toEqual(expect.objectContaining({id: 123}));
    });
  });
});
