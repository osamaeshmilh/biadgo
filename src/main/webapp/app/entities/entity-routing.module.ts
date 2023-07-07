import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'dashboard',
        data: {pageTitle: 'Dashboard'},
        loadChildren: () => import('./dashboard/dashboard.routes'),
      },
      {
        path: 'app-setting',
        data: {pageTitle: 'AppSettings'},
        loadChildren: () => import('./app-setting/app-setting.routes'),
      },
      {
        path: 'activation',
        data: {pageTitle: 'Activations'},
        loadChildren: () => import('./activation/activation.routes'),
      },
      {
        path: 'customer',
        data: {pageTitle: 'Customers'},
        loadChildren: () => import('./customer/customer.routes'),
      },
      {
        path: 'customer-wallet',
        data: {pageTitle: 'CustomerWallets'},
        loadChildren: () => import('./customer-wallet/customer-wallet.routes'),
      },
      {
        path: 'cart',
        data: {pageTitle: 'Carts'},
        loadChildren: () => import('./cart/cart.routes'),
      },
      {
        path: 'category',
        data: {pageTitle: 'Categories'},
        loadChildren: () => import('./category/category.routes'),
      },
      {
        path: 'city',
        data: {pageTitle: 'Cities'},
        loadChildren: () => import('./city/city.routes'),
      },
      {
        path: 'coupon',
        data: {pageTitle: 'Coupons'},
        loadChildren: () => import('./coupon/coupon.routes'),
      },
      {
        path: 'cuisine',
        data: {pageTitle: 'Cuisines'},
        loadChildren: () => import('./cuisine/cuisine.routes'),
      },
      {
        path: 'delivery-address',
        data: {pageTitle: 'DeliveryAddresses'},
        loadChildren: () => import('./delivery-address/delivery-address.routes'),
      },
      {
        path: 'driver',
        data: {pageTitle: 'Drivers'},
        loadChildren: () => import('./driver/driver.routes'),
      },
      {
        path: 'driver-location',
        data: {pageTitle: 'DriverLocations'},
        loadChildren: () => import('./driver-location/driver-location.routes'),
      },
      {
        path: 'driver-review',
        data: {pageTitle: 'DriverReviews'},
        loadChildren: () => import('./driver-review/driver-review.routes'),
      },
      {
        path: 'driver-wallet',
        data: {pageTitle: 'DriverWallets'},
        loadChildren: () => import('./driver-wallet/driver-wallet.routes'),
      },
      {
        path: 'favorite-restaurant',
        data: {pageTitle: 'FavoriteRestaurants'},
        loadChildren: () => import('./favorite-restaurant/favorite-restaurant.routes'),
      },
      {
        path: 'food',
        data: {pageTitle: 'Foods'},
        loadChildren: () => import('./food/food.routes'),
      },
      {
        path: 'food-extra',
        data: {pageTitle: 'FoodExtras'},
        loadChildren: () => import('./food-extra/food-extra.routes'),
      },
      {
        path: 'food-ingredient',
        data: {pageTitle: 'FoodIngredients'},
        loadChildren: () => import('./food-ingredient/food-ingredient.routes'),
      },
      {
        path: 'food-image',
        data: {pageTitle: 'FoodImages'},
        loadChildren: () => import('./food-image/food-image.routes'),
      },
      {
        path: 'food-order',
        data: {pageTitle: 'FoodOrders'},
        loadChildren: () => import('./food-order/food-order.routes'),
      },
      {
        path: 'notification',
        data: {pageTitle: 'Notifications'},
        loadChildren: () => import('./notification/notification.routes'),
      },
      {
        path: 'order',
        data: {pageTitle: 'Orders'},
        loadChildren: () => import('./order/order.routes'),
      },
      {
        path: 'order-history',
        data: {pageTitle: 'OrderHistories'},
        loadChildren: () => import('./order-history/order-history.routes'),
      },
      {
        path: 'payment-method',
        data: {pageTitle: 'PaymentMethods'},
        loadChildren: () => import('./payment-method/payment-method.routes'),
      },
      {
        path: 'referral',
        data: {pageTitle: 'Referrals'},
        loadChildren: () => import('./referral/referral.routes'),
      },
      {
        path: 'restaurant',
        data: {pageTitle: 'Restaurants'},
        loadChildren: () => import('./restaurant/restaurant.routes'),
      },
      {
        path: 'restaurant-distance-price',
        data: {pageTitle: 'RestaurantDistancePrices'},
        loadChildren: () => import('./restaurant-distance-price/restaurant-distance-price.routes'),
      },
      {
        path: 'restaurant-zone-price',
        data: {pageTitle: 'RestaurantZonePrices'},
        loadChildren: () => import('./restaurant-zone-price/restaurant-zone-price.routes'),
      },
      {
        path: 'restaurant-wallet',
        data: {pageTitle: 'RestaurantWallets'},
        loadChildren: () => import('./restaurant-wallet/restaurant-wallet.routes'),
      },
      {
        path: 'restaurant-review',
        data: {pageTitle: 'RestaurantReviews'},
        loadChildren: () => import('./restaurant-review/restaurant-review.routes'),
      },
      {
        path: 'restaurant-schedule',
        data: {pageTitle: 'RestaurantSchedules'},
        loadChildren: () => import('./restaurant-schedule/restaurant-schedule.routes'),
      },
      {
        path: 'slider',
        data: {pageTitle: 'Sliders'},
        loadChildren: () => import('./slider/slider.routes'),
      },
      {
        path: 'transaction',
        data: {pageTitle: 'Transactions'},
        loadChildren: () => import('./transaction/transaction.routes'),
      },
      {
        path: 'zone',
        data: {pageTitle: 'Zones'},
        loadChildren: () => import('./zone/zone.routes'),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
