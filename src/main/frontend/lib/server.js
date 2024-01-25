

async function fetchData(url, options) {
    try {
        const response = await fetch(url, options);

        if (response.ok) {
            const data = await response.json();
            console.log("接口调用成功:", data);
            return data.data;
        } else {
            console.error('接口调用失败');
            return null;
        }
    } catch (error) {
        console.error('发生错误:', error);
        return null;
    }
}


export async function getUserList() {
    const url = 'http://localhost:8080/ticketApi/userPage';
    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            pageSize:10,
            pageIndex:1,
        })
    };
    return await fetchData(url, options);
}