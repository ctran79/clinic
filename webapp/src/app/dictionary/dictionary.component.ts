import {Component, OnInit} from '@angular/core';
import {DictService} from "../service/dict.service";
import {Dictionary} from "../domain/dictionary";

@Component({
  selector: 'app-dictionary',
  templateUrl: './dictionary.component.html',
  styleUrls: ['./dictionary.component.css']
})
export class DictionaryComponent implements OnInit {

  dictList: Dictionary[] = [];
  selectedDict: Dictionary | undefined;

  constructor(public dictService: DictService) {
  }

  ngOnInit(): void {
    this.loadDictionaries();
  }

  onChangeDictionary(dictId: number) {
    this.selectedDict = this.dictList.find(dict => dict.id === dictId);
  }

  private loadDictionaries() {
    this.dictService.getAllModel().subscribe(result => {
      this.dictList = result;
    });
  }
}

