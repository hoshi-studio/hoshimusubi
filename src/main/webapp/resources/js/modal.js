document.addEventListener("DOMContentLoaded", function() {
  const form = document.getElementById("loginForm");
  const idInput = document.getElementById("id");
  const pwInput = document.getElementById("password");
  
    idInput.addEventListener("keydown", function(e) {
        if (e.key === " ") {
          e.preventDefault();
        }
      });

    idInput.addEventListener("input", function() {
        this.value = this.value.replace(/\s/g, "");
      });
  
    pwInput.addEventListener("keydown", function(e) {
      if (e.key === " ") {
        e.preventDefault();
      }
    });

    pwInput.addEventListener("input", function() {
      this.value = this.value.replace(/\s/g, "");
    });

  form.addEventListener("submit", function(e) {
    // ID 빈값 검사
    if (idInput.value.trim() === "") {
      showModal("errorid");
      idInput.focus();
      e.preventDefault(); // 서버 전송 막음
      return;
    }

    // PW 빈값 검사
    if (pwInput.value.trim() === "") {
      showModal("errorpw");
      pwInput.focus();
      e.preventDefault();
      return;
    }

    // 둘 다 OK면 서버로 제출
  });
});
  
function showModal(type) {
    document.getElementById('modalOverlay').style.display = 'block';
    document.getElementById('successModal').style.display = 'none';
    document.getElementById('errorModal').style.display = 'none';
    document.getElementById('erroridModal').style.display = 'none';
    document.getElementById('errorpwModal').style.display = 'none';

    if (type === 'success') {
      document.getElementById('successModal').style.display = 'block';
    } else if (type === 'errorid') {
      document.getElementById('erroridModal').style.display = 'block';
    } else if (type === 'errorpw') {
      document.getElementById('errorpwModal').style.display = 'block';
    } else {
      document.getElementById('errorModal').style.display = 'block';
    }
  }

  
function closeModal() {
      document.getElementById('modalOverlay').style.display = 'none';
      document.getElementById('successModal').style.display = 'none';
	  document.getElementById('erroridModal').style.display = 'none';
	  document.getElementById('errorpwModal').style.display = 'none';
      document.getElementById('errorModal').style.display = 'none';
    }