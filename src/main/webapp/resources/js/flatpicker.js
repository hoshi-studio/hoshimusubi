document.addEventListener("DOMContentLoaded", function() {

  flatpickr("#birthDate", {
    locale: "ja",
    dateFormat: "Y-m-d",
	altInput: true,
	altFormat: "Y年m月d日"   
  });

});