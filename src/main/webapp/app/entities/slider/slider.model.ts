export interface ISlider {
  id: number;
  details?: string | null;
  detailsAr?: string | null;
  detailsEn?: string | null;
  menuOrder?: number | null;
  imageUrl?: string | null;
  image?: string | null;
  imageContentType?: string | null;
  url?: string | null;
  restaurantId?: number | null;
  categoryId?: number | null;
  foodId?: number | null;
  notes?: string | null;
}

export type NewSlider = Omit<ISlider, 'id'> & { id: null };
