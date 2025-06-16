
  function showModal(type) {
    document.getElementById('modalOverlay').style.display = 'block';
    if (type === 'success') {
      document.getElementById('successModal').style.display = 'block';
    } else {
      document.getElementById('errorModal').style.display = 'block';
    }
  }
  function closeModal() {
    document.getElementById('modalOverlay').style.display = 'none';
    document.getElementById('successModal').style.display = 'none';
    document.getElementById('errorModal').style.display = 'none';
  }
