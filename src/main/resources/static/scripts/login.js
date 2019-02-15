function login() {
  const emailValue = document.getElementById('email').value;
  const passwordValue = document.getElementById('password').value;
  const message = document.getElementById('message');
  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState === 4 && this.status === 200) {
      const parsedRes = JSON.parse(this.responseText);
      if (parsedRes.error) {
        return displayError(parsedRes.message);
      }
      if (!parsedRes.success) {
        return message.innerHTML = parsedRes.message;
      }
      location.href = '/home';
    }
  };
  xhttp.open('POST', '/login', true);
  xhttp.setRequestHeader('Content-Type', 'application/json');
  const data = JSON.stringify({
    email: emailValue,
    password: passwordValue,
  });
  console.log(data);
  xhttp.send(data);
}