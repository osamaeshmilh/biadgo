import {TestBed} from '@angular/core/testing';
import {provideRouter, withComponentInputBinding} from '@angular/router';
import {RouterTestingHarness, RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {CuisineDetailComponent} from './cuisine-detail.component';

describe('Cuisine Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CuisineDetailComponent, RouterTestingModule.withRoutes([], {bindToComponentInputs: true})],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: CuisineDetailComponent,
              resolve: {cuisine: () => of({id: 123})},
            },
          ],
          withComponentInputBinding()
        ),
      ],
    })
      .overrideTemplate(CuisineDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load cuisine on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', CuisineDetailComponent);

      // THEN
      expect(instance.cuisine).toEqual(expect.objectContaining({id: 123}));
    });
  });
});
