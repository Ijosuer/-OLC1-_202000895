import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'MFMScript';
  fileToUpload: File | null = null;
  
  callme(){
    console.log('')
  }
  handleFileInput(files: FileList) {
    console.log('simopn pa entraaa')
    this.fileToUpload = files.item(0);
  }
}
