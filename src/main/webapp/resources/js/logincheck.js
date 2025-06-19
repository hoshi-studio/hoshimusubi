document.addEventListener("DOMContentLoaded", function() {
  const form = document.getElementById("loginForm");
  
  const username = document.getElementById('id').value;
  const password = document.getElementById('password').value;
  
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
	
	if (!username) {
	              showModal('errorid');
				  username.focus();
				  e.preventDefault();
				  return;  // 폼 제출을 막음
				  
	          }
	if (!password) {
				showModal('errorpw');  // error 모달을 띄움
				password.focus();
				e.preventDefault();
				return;
			 }
  });
});
