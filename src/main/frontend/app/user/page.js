import {getUserList} from "@/lib/server";
import {Empty, Table} from "antd";


const columns = [
    {
        title: '姓名',
        dataIndex: 'name',
        key: 'name',
    },
    {
        title: '手机号',
        dataIndex: 'mobile',
        key: 'mobile',
    },
    {
        title: '工作状态',
        dataIndex: 'workStatusDesc',
        key: 'workStatusDesc',
    },
    {
        title: '能力等级',
        dataIndex: 'abilityLevelDesc',
        key: 'abilityLevelDesc',
    },
    {
        title: '操作',
        dataIndex: 'action',
        key: 'action',
    }
];

export default async function Page() {

    const data = await getUserList();
    const {items} = data

    if (!data && items.length === 0) {
        return <Empty />;
    }

    return (
        <Table dataSource={items} columns={columns} />
    )
}