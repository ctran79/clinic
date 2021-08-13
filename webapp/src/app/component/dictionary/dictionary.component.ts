import {Component, OnInit} from '@angular/core';
import {DictionaryService} from "../../service/dictionary.service";
import {Dictionary} from "../../domain/dictionary";

@Component({
  selector: 'app-dictionary',
  templateUrl: './dictionary.component.html',
  styleUrls: ['./dictionary.component.css']
})
export class DictionaryComponent implements OnInit {

  dictList: Dictionary[] = [];
  selectedDict: Dictionary | undefined;

  constructor(public dictService: DictionaryService) {
  }

  ngOnInit(): void {
    this.loadDictionaries();
  }

  onChangeDictionary(dictId: number) {
    this.selectedDict = this.dictList.find(dict => dict.id === dictId);
  }

  saveDictionary(dictionary: Dictionary) {
    this.dictService.saveModel(dictionary).subscribe(value => this.loadDictionaries());
  }

  private loadDictionaries() {
    this.dictService.getAllModel().subscribe(result => {
      this.dictList = result;
      if (this.selectedDict) {
        this.selectedDict = this.dictList.find(dict => dict.id === this.selectedDict?.id);
      }
    });
  }
}

