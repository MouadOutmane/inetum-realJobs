import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'reverseArraySort'
})
export class ReverseArraySortPipe implements PipeTransform {

  transform(array: any, field: string): any[] {
    if (!Array.isArray(array)) {
      return null;
    }
    array.sort((a: any, b: any) => {
      if (a[field] < b[field]) {
        return 1;
      } else if (a[field] > b[field]) {
        return -1;
      } else {
        return 0;
      }
    });
    return array;
  }
}
