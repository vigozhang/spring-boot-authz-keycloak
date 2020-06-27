<h1>Admin View</h1>
<div>
    <#if authz.hasScopePermission("delete")>
        <a href="/admin/delete">delete</a>
    </#if>
</div>