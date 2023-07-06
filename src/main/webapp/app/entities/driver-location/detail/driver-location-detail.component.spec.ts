import {TestBed} from '@angular/core/testing';
import {provideRouter, withComponentInputBinding} from '@angular/router';
import {RouterTestingHarness, RouterTestingModule} from '@angular/router/testing';
import {of} from 'rxjs';

import {DriverLocationDetailComponent} from './driver-location-detail.component';

describe('DriverLocation Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DriverLocationDetailComponent, RouterTestingModule.withRoutes([], {bindToComponentInputs: true})],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: DriverLocationDetailComponent,
              resolve: {driverLocation: () => of({id: 123})},
            },
          ],
          withComponentInputBinding()
        ),
      ],
    })
      .overrideTemplate(DriverLocationDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load driverLocation on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', DriverLocationDetailComponent);

      // THEN
      expect(instance.driverLocation).toEqual(expect.objectContaining({id: 123}));
    });
  });
});
