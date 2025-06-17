document.addEventListener("DOMContentLoaded", function() {
  const form = document.getElementById("signupForm");
  const birthDate = document.getElementById("birthDate");
  const nickname = document.getElementById("nickname");

  const modalOverlay = document.querySelector(".modal-overlay");
  const modal = document.querySelector(".modal");
  const modalMessage = document.getElementById("modal-message");
  const modalCloseBtn = document.getElementById("modal-close-btn");

  function showModal(message) {
    modalMessage.textContent = message;
    modalOverlay.style.display = "block";
    modal.style.display = "block";
  }

  modalCloseBtn.addEventListener("click", function() {
    modalOverlay.style.display = "none";
    modal.style.display = "none";
  });

  form.addEventListener("submit", function(e) {
    const gender = form.querySelector('input[name="gender"]:checked');

    if (!birthDate.value) {
      showModal("生年月日を入力してください。");
      birthDate.focus();
      e.preventDefault();
      return;
    }

    if (!nickname.value) {
      showModal("ニックネームを入力してください。");
      nickname.focus();
      e.preventDefault();
      return;
    }

    if (!gender) {
      showModal("性別を選択してください。");
      e.preventDefault();
      return;
    }
  });
});