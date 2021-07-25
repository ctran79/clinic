export interface PagedSearchResult<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
}
