

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
    const url = 'http://localhost:8080/ticketApi/userpage';
    const options = {
        method: 'POST',
        cache: 'no-store',
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

export async function getTicketPage() {
    const url = 'http://localhost:8080/ticketApi/ticketpage';
    const options = {
        method: 'POST',
        cache: 'no-store',
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

export async function getUserIdsByTargetDate(targetDate){
    const url = `http://localhost:8080/ticketApi/getUserIdsByTargetDate?targetDate=${targetDate}`;
    const options = {
        cache: 'no-store',
        headers: {
            'Content-Type': 'application/json',
        },
    }
    return await fetchData(url, options);
}

export async function countTodayTicket(day) {
    const url = `http://localhost:8080/ticketApi/countTodayTicket?day=${day}`;
    const options = {
        cache: 'no-store',
        headers: {
            'Content-Type': 'application/json',
        },
    }
    return await fetchData(url, options);
}

export async function getTicketStatusCountForCurrentWeek(){
    const url = 'http://localhost:8080/ticketApi/getTicketStatusCountForCurrentWeek';
    const options = {
        cache: 'no-store',
        headers: {
            'Content-Type': 'application/json',
        },
    }
    return await fetchData(url, options);
}


export async function scheduleList(userId){
    let url = 'http://localhost:8080/ticketApi/scheduleList';

    // 检查 userId 是否为 null
    if (userId !== null) {
        // 如果不为 null，则拼接到 URL 中
        url += `?userId=${userId}`;
    }
    const options = {
        cache: 'no-store',
        headers: {
            'Content-Type': 'application/json',
        },
    }
    return await fetchData(url, options);
}