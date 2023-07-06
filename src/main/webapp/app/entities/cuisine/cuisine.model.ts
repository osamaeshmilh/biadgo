export interface ICuisine {
  id: number;
  name?: string | null;
  nameAr?: string | null;
  nameEn?: string | null;
  isActive?: boolean | null;
}

export type NewCuisine = Omit<ICuisine, 'id'> & { id: null };
