document.addEventListener("DOMContentLoaded", function() {
	
const fileInput = document.getElementById('profileImage');
const fileName = document.getElementById('file-name');

  fileInput.addEventListener('change', function() {
    if (fileInput.files.length > 0) {
      fileName.textContent = fileInput.files[0].name;
    } else {
      fileName.textContent = '選択されたファイルはありません';
    }
  });
 
});