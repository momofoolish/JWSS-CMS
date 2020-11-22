import React, { Fragment } from 'react';
import Button from '../button/button';
import './list.css';

// 导出组件
function List(props) {
    const { dataSource, loading } = props;
    return (
        <ul className="com-public-list">
            {
                loading ? 'Loading...' :
                    <DataRender dataSource={dataSource}
                        onAlterClick={props.onAlterClick}
                        onDeleteClick={props.onDeleteClick}
                    />
            }
        </ul>
    )
}

// 数据渲染组件
function DataRender(props) {
    const { dataSource } = props;
    return (
        <Fragment>
            <li className="com-public-title">
                <span>标题</span>
                <span>描述</span>
                <span>访问量</span>
                <span>操作</span>
            </li>
            {
                dataSource === '' ? '没有数据' :
                    dataSource.map((item, index) => {
                        return (
                            <li className="com-public-content" key={index}>
                                <span>{item.title}</span>
                                <span>{item.description}</span>
                                <span>{item.readsNumber}</span>
                                <span>
                                    <Button value="修改" width="64px"
                                        onClick={() => { props.onAlterClick(item.id) }}
                                    />
                                    <Button value="删除" width="64px"
                                        onClick={() => { props.onDeleteClick(item.id) }}
                                    />
                                </span>
                            </li>
                        )
                    })
            }
        </Fragment>
    )
}

export default List;