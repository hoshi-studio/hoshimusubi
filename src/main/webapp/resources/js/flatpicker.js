document.addEventListener("DOMContentLoaded", function() {
  const birthDate = document.getElementById("birthDate");
  const birthdate = document.getElementById("birthdate");

  if (birthDate) {
    flatpickr("#birthDate", {
      locale: "ja",
      dateFormat: "Y-m-d"
    });
  }

  if (birthdate) {
    flatpickr("#birthdate", {
      locale: "ja",
      dateFormat: "Y-m-d"
    });
  }
});
