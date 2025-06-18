document.addEventListener("DOMContentLoaded", function() {
  const form = document.getElementById("signupForm");

  const email = document.getElementById("email");
  const birthDate = document.getElementById("birthDate");
  const birthdate = document.getElementById("birthdate");
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
  
  function isValidEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  }

  form.addEventListener("submit", function(e) {
    const emailValue = email ? email.value : "";
    const birthDateValue = birthDate ? birthDate.value : "";
    const birthdateValue = birthdate ? birthdate.value : "";
    const gender = form.querySelector('input[name="gender"]:checked');

    if (!emailValue) {
      showModal("メールアドレスを入力してください。");
      email.focus();
      e.preventDefault();
      return;
    }
	
	if (!isValidEmail(emailValue)) {
	  showModal("有効なメールアドレスを入力してください。");
	  email.focus();
	  e.preventDefault();
	  return;
	}

    if (!birthDateValue && !birthdateValue) {
      showModal("生年月日を入力してください。");
      if (birthDate && !birthDate.value) birthDate.focus();
      else if (birthdate && !birthdate.value) birthdate.focus();
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
