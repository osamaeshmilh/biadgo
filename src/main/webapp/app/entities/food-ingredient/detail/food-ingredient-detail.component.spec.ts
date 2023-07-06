import {TestBed} from '@angular/core/testing';
import {provideRouter, withComponentInputBinding} from '@angular/router';
import {RouterTestingHarness, RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {FoodIngredientDetailComponent} from './food-ingredient-detail.component';

describe('FoodIngredient Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FoodIngredientDetailComponent, RouterTestingModule.withRoutes([], {bindToComponentInputs: true})],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: FoodIngredientDetailComponent,
              resolve: {foodIngredient: () => of({id: 123})},
            },
          ],
          withComponentInputBinding()
        ),
      ],
    })
      .overrideTemplate(FoodIngredientDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load foodIngredient on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', FoodIngredientDetailComponent);

      // THEN
      expect(instance.foodIngredient).toEqual(expect.objectContaining({id: 123}));
    });
  });
});
