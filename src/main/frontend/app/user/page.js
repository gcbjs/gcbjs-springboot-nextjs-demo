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
    },{
        title: '操作',
        dataIndex: 'action',
        key: 'action',
    }
];

export default async function Page() {

    const data = await getUserList();

    if (!data) {
        return <Empty />;
    }

    return (
        <Table dataSource={data} columns={columns} />
    )
}