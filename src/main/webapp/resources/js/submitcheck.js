document.addEventListener("DOMContentLoaded", function() {
  const form = document.getElementById("signupForm");

  const email = document.getElementById("email");
  
  const password = document.getElementById("password");
  
  const birthDate = document.getElementById("birthDate");
  const birthdate = document.getElementById("birthdate");
  const nickname = document.getElementById("nickname");

  const modalOverlay = document.querySelector(".modal-overlay");
  const modal = document.querySelector(".modal");
  const modalMessage = document.getElementById("modal-message");
  const modalCloseBtn = document.getElementById("modal-close-btn");
  
  
  if (password) {
  password.addEventListener("keydown", function(e) {
          if (e.key === " ") {
            e.preventDefault();
          }
        });

      password.addEventListener("input", function() {
          this.value = this.value.replace(/\s/g, "");
        });
		
		}
		
		nickname.addEventListener("keydown", function(e) {
		          if (e.key === " ") {
		            e.preventDefault();
		          }
		        });

		      nickname.addEventListener("input", function() {
		          this.value = this.value.replace(/\s/g, "");
		        });
  
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
	e.preventDefault();
	
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
	
	if (password){
	if (!password.value) {
	      showModal("パスワードを入力してください。");
	      email.focus();
	      e.preventDefault();
	      return;
	    }
	}
	

    if (!birthDateValue && !birthdateValue) {
      showModal("生年月日を入力してください。");
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
	  
	  fetch(`/checkNickname?nickname=${encodeURIComponent(nickname.value)}`)
	    .then(response => response.json())
	    .then(data => {
			console.log(data);
	      if (data.exists) {
	        showModal("既に使用されているニックネームです。");
	        nickname.focus();
	        return;
	      }

	      // ✅ 중복 아니면 제출!
	      form.submit();
	    })
	    .catch(err => {
	      console.error(err);
	      showModal("에러 발생");
	    });
    
  });
});
