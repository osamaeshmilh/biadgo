import {TestBed} from '@angular/core/testing';
import {provideRouter, withComponentInputBinding} from '@angular/router';
import {RouterTestingHarness, RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {ActivationDetailComponent} from './activation-detail.component';

describe('Activation Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ActivationDetailComponent, RouterTestingModule.withRoutes([], {bindToComponentInputs: true})],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: ActivationDetailComponent,
              resolve: {activation: () => of({id: 123})},
            },
          ],
          withComponentInputBinding()
        ),
      ],
    })
      .overrideTemplate(ActivationDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load activation on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', ActivationDetailComponent);

      // THEN
      expect(instance.activation).toEqual(expect.objectContaining({id: 123}));
    });
  });
});
