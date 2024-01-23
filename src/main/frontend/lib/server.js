
export async function getUserList() {
    const response = await fetch("http://localhost:8080/api/userList");
    return response.json();
}