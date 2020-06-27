<h1>Customer View</h1>
<div>
    <#if authz.hasScopePermission("delete")>
        <a href="/customer/delete">delete</a>
    </#if>
</div>